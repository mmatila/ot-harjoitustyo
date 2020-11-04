package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void kortinSaldoAlussaOikein() {
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void rahanLataaminenKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(10);
        assertEquals("saldo: 0.20", kortti.toString());
    }
    
    @Test
    public void saldoVaheneeJosRahaaOnTarpeeksi() {
        kortti.otaRahaa(10);
        assertEquals(0, kortti.saldo());
    }
    
    @Test
    public void saldoEiVaheneJosRahaaEiOleTarpeeksi() {
        kortti.otaRahaa(1000);
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void palauttaaTrueJosRahaaRiitaa() {
        assertEquals(true, kortti.otaRahaa(10));
    }
    
    @Test
    public void palauttaaFalseJosRahatEiRiita() {
        assertEquals(false, kortti.otaRahaa(200));
    }
}
