package com.example;

import com.example.impl.ProcessImpl;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class CustomMain
{
    public static void main(String ...args)
    {
        Quarkus.run(CustomApp.class, args);
    }

    public static class CustomApp implements QuarkusApplication
    {
        public int run(String ...args) throws Exception
        {
            ProcessImpl.setHashDictionary();
            System.out.println("Corriendo desde custom app");
            Quarkus.waitForExit();
            return 0;
        }
    }
}
