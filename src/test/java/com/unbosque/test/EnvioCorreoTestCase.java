package com.unbosque.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.unbosque.utils.CorreoManager;

public class EnvioCorreoTestCase {
    private CorreoManager correoManager;

    @Before
    public void setUp() {
        correoManager = new CorreoManager();
    }

    @Test
    public void testEnviarCorreo() {
        String destinatario = "dvmurcia@unbosque.edu.co";
        String usuario = "Daniela";
        String password = "Pgr1234";

        boolean resultado = correoManager.enviarCorreo(destinatario, usuario, password);

        assertTrue(resultado);
    }
}
