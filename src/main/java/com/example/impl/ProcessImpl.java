package com.example.impl;

import com.example.model.Columns;
import com.example.model.LayoutBase;
import javax.inject.Singleton;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Objects;

@Singleton
public class ProcessImpl
{
    private static final Hashtable<String, String> hs = new Hashtable<>();

    public LayoutBase process()
    {

        try
        {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream is = classloader.getResourceAsStream("jsonGraph.txt");

            LayoutBase layoutBase = new LayoutBase();
            StringBuilder pojo = new StringBuilder();
            String str;
            int order = 1;

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(is))))
            {
                List<Columns> layout = new ArrayList<>();

                while ((str = reader.readLine()) != null)
                {
                    Columns columns = new Columns();

                    String [] l = str.split(":");

                    pojo.append("@GraphQLData(\"").append(l[0].trim()).append("\")").append("\n");
                    pojo.append("private").append(" ").append(l[1].trim()).append(" ").append(l[0].trim()).append(";").append("\n\n");

                    System.out.println(pojo);

                    columns.setField(l[0].trim());
                    columns.setExportable(true);
                    columns.setType(l[1].trim());
                    columns.setSortable(true);
                    columns.setRequired(true);

                    String finalPhrase = camelCase(l[0]);

                    columns.setTitle(finalPhrase);
                    columns.setDescription(finalPhrase);
                    columns.setOrder(order);

                    layout.add(columns);

                    order++;
                }

                layoutBase.setColumns(layout);

                return layoutBase;
            }
        }
        catch (Exception ex)
        {
            System.out.println("Se genero un error al procesar el archivo: " + ex);
        }

        return new LayoutBase();
    }


    private String camelCase(String cc)
    {
        try
        {
            StringBuilder sb = new StringBuilder();

            for (String w : cc.split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])"))
            {
                sb.append(hs.getOrDefault(w.toLowerCase(), w.toLowerCase())).append(" ");
            }

            return upperCaseFirst(sb.toString().trim());
        }
        catch (Exception ex)
        {
            System.err.println("Error al procesar frase: "+ ex.getMessage());
            return null;
        }
    }

    private String upperCaseFirst(String phrase)
    {
        try
        {
            String firstLtr = phrase.substring(0, 1);
            String restLtrs = phrase.substring(1);

            return firstLtr.toUpperCase() + restLtrs;
        }
        catch (Exception ex)
        {
            System.err.println("Error al procesar la primera palabra en mayuscula :" + ex.getMessage());
            return null;
        }
    }


    public static void setHashDictionary()
    {

        hs.put("cod", "c\u00f3digo");
        hs.put("promocion", "promoci\u00f3n");
        hs.put("transaccion", "transacci\u00f3n");
        hs.put("deposito", "dep\u00f3sito");
        hs.put("afiliacion", "afiliaci\u00f3n");
        hs.put("autorizacion", "autorizaci\u00f3n");
        hs.put("modificacion", "modificaci\u00f3n");
        hs.put("terminacion", "terminaci\u00f3n");
        hs.put("electronico", "electr\u00f3nico");
        hs.put("fh", "fecha");
        hs.put("hr", "hora");
        hs.put("categoria", "categor\u00EDa");
        hs.put("numero", "n\u00FAmero");
        hs.put("envio", "env\u00EDo");
        hs.put("ultimo", "\u00FAltimo");
        hs.put("ultima", "\u00FAltima");
    }
}
