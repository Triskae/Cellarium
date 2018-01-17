package classes;

import util.CellariumUtil;

public enum Couleurs {
    Rouge,
    Blanc,
    Rose,
    Autres;

    private static final int size = values().length;

    public static final String[] SORTED_STRING_ARRAY = CellariumUtil.sortClearBOM(toStringArray());

    public static String[] toStringArray()
    {
        Couleurs[] coul = values();
        String[] str = new String[size];

        for (int i=0;i<size;i++) str[i] = coul[i].toString();

        return str;
    }
}
