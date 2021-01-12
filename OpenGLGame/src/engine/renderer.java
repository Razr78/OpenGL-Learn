package engine;

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
import org.lwjgl.opengl.GL30;

public class renderer
{
    public void renderModel(Model model)
    {
        GL30.glBindVertexArray(model.getVertexArrayID());
        GL11.glDrawArrays(GL_TRIANGLES, 0, model.getVertexCount());
        GL30.glBindVertexArray(0);
    }
}
