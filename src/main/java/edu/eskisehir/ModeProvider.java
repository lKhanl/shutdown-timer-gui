package edu.eskisehir;

public class ModeProvider {

    private Mode mode;

    public ModeProvider(Mode mode) {
        this.mode = mode;
    }

    public String getBgColor() {
        if (mode == Mode.DARK)
            return "#212121";
        else return "light";
    }

    public String getTextColor() {
        if (mode == Mode.DARK)
            return "white";
        else return "#121212";
    }

    public void switchMode() {
        if (mode == Mode.DARK)
            mode = Mode.LIGHT;
        else
            mode = Mode.DARK;
    }
}
