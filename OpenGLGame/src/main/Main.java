package main;

import engine.Model;
import engine.renderer;
import org.joml.Vector3f;
import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.awt.*;
import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;
import org.lwjgl.opengl.GL30;


public class Main
{
    public static final int WIDTH = 1920, HEIGHT = 1080, FPS = 60;
    public static renderer renderer = new renderer();

    public static void main(String[] args)
    {
        window window = new window(WIDTH, HEIGHT, FPS , "lol");
        window.create();
        window.setBackgroundColor(1.0f, 0.0f, 0.0f);

        Model model = new Model(new float[]
        {
             -0.5f, 0.5f, 0.0f, //top left
             0.5f, 0.5f, 0.0f, //top right
             0.5f, -0.5f, 0.0f, //bottom right

             -0.5f, -0.5f, 0.0f, //bottom left
             0.5f, -0.5f, 0.0f, //bottom right
             -0.5f, 0.5f, 0.0f, //to left
        });
        model.create();

        while (!window.closed())
        {
            if(window.isUpdating())
            {
                window.update();
                renderer.renderModel(model);
                window.swapBuffers();
            }
        }

        window.stop();
        model.remove();
    }
}
