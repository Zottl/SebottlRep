package controller.input;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class FocusManager implements WindowListener
{
    private Keyboard kb;
    private Mouse ms;

    public FocusManager(Keyboard kb, Mouse ms)
    {
        super();
        this.kb = kb;
        this.ms = ms;
    }

    @Override
    public void windowActivated(WindowEvent e)
    {
    }

    @Override
    public void windowClosed(WindowEvent e)
    {
    }

    @Override
    public void windowClosing(WindowEvent e)
    {
    }

    @Override
    public void windowDeactivated(WindowEvent e)
    {
        kb.reset();
        ms.reset();
    }

    @Override
    public void windowDeiconified(WindowEvent e)
    {
    }

    @Override
    public void windowIconified(WindowEvent e)
    {
    }

    @Override
    public void windowOpened(WindowEvent e)
    {
    }
}
