package ru.vovac.game.utils;

public enum LangsEnum{
    RU("RU"),
    EN("EN");

    private String directory;

    LangsEnum(String directory) {
        this.directory = directory;
    }

    public String getDirectory() {
        return directory;
    }

    @Override
    public String toString() {
        return "LangsEnum{" +
                "directory='" + directory + '\'' +
                '}';
    }
}
