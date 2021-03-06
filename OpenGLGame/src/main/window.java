package main;

import org.joml.Vector3f;
import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class window
{
    //variables
    private int width, height;
    private double fps_cap, time, processedTime;
    private String title;
    private long window;
    private Vector3f backgroundColor;
    private boolean[] keys = new boolean[GLFW.GLFW_KEY_LAST];
    private boolean[] mouseButtons = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];

    //constructor
    public window(int width, int height, int fps, String title)
    {
        this.width = width;
        this.height = height;
        this.title = title;
        fps_cap = fps;
        backgroundColor = new Vector3f(0.0f, 0.0f, 0.0f);
    }

    //creating the window
    public void create()
    {
        if(!GLFW.glfwInit())
        {
            //error for not being able to initialize GLFW
            System.err.println("ERROR: Couldn't initialize GLFW");
            System.exit(-1);
        }

        GLFW.glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        GLFW.glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
        window = GLFW.glfwCreateWindow(width, height, title, 0, 0);

        //error that displays if the window could not be created
        if (window == 0)
        {
            System.err.println("ERROR: Window couldn't be created");
            System.exit(-1);
        }

        //this centers the window
        GLFWVidMode videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
        GLFW.glfwSetWindowPos(window, (videoMode.width() - width) / 2, (videoMode.height() - height) / 2);

        GLFW.glfwShowWindow(window);

        time = getTime();
        processedTime = 0;

        GLFW.glfwMakeContextCurrent(window);
        GL.createCapabilities();
    }

    //closing the window
    public boolean closed()
    {
        return GLFW.glfwWindowShouldClose(window);
    }

    public void update()
    {
        for (int i = 0; i < GLFW.GLFW_KEY_LAST; i++) keys[i] = isKeyDown(i);
        for (int i = 0; i < GLFW.GLFW_MOUSE_BUTTON_LAST; i++) mouseButtons[i] = isMouseDown(i);
        GL11.glClearColor(backgroundColor.x, backgroundColor.y, backgroundColor.z, 1f);
        GL11.glClear(GL_COLOR_BUFFER_BIT);
        GLFW.glfwPollEvents();
    }

    public void stop()
    {
        GLFW.glfwTerminate();
    }

    public void swapBuffers()
    {
        GLFW.glfwSwapBuffers(window);
    }

    public double getTime()
    {
        return (double) System.nanoTime() / (double) 1000000000;
    }

    public boolean isKeyDown(int keycode)
    {
        return GLFW.glfwGetKey(window, keycode) == 1;
    }

    public boolean isMouseDown(int mouseButton)
    {
        return GLFW.glfwGetMouseButton(window, mouseButton) == 1;
    }

    public boolean isKeyPressed(int keycode)
    {
        return isKeyDown(keycode) && !keys[keycode];
    }

    public boolean isKeyReleased(int keycode)
    {
        return !isKeyDown(keycode) && keys[keycode];
    }

    public boolean isMousePressed(int mouseButton)
    {
        return isMouseDown(mouseButton) && !mouseButtons[mouseButton];
    }

    public boolean isMouseReleased(int mouseButton)
    {
        return !isMouseDown(mouseButton) && mouseButtons[mouseButton];
    }

    public double getMouseX()
    {
        DoubleBuffer buffer = BufferUtils.createDoubleBuffer(1);
        GLFW.glfwGetCursorPos(window, buffer, null);
        return buffer.get(0);
    }

    public double getMouseY()
    {
        DoubleBuffer buffer = BufferUtils.createDoubleBuffer(1);
        GLFW.glfwGetCursorPos(window, null, buffer);
        return buffer.get(0);
    }

    public boolean isUpdating()
    {
        double nextTime = getTime();
        double passedTime = nextTime - time;
        processedTime += passedTime;
        time = nextTime;

        while (processedTime > 1.0/fps_cap)
        {
            processedTime -= 1.0/fps_cap;
            return true;
        }
        return false;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public double getFPS()
    {
        return fps_cap;
    }

    public String getTitle()
    {
        return title;
    }

    public long getWindow()
    {
        return window;
    }

    public void setBackgroundColor(float r, float g, float b)
    {
        backgroundColor = new Vector3f(r, g ,b);
    }
}
