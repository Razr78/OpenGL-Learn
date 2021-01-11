package main;

import engine.window;
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

public class Main
{
    public static final int WIDTH = 1920, HEIGHT = 1080, FPS = 60;

    public static void main(String[] args)
    {
        window window = new window(WIDTH, HEIGHT, FPS , "lol");
        window.create();

        while (!window.closed())
        {
            if(window.isUpdating())
            {
                window.update();

                window.swapBuffers();
            }
        }
    }
}
