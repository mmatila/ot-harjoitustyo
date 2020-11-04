/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author mmatila
 */
public class KassapaateTest {

    Kassapaate paate;
    Maksukortti kortti;

    @Before
    public void setUp() {
        paate = new Kassapaate();
        kortti = new Maksukortti(1000);
    }
    
    @Test
    public void uudenPaatteenRahamaaraOikein() {
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void uudenPaatteenMyydytLounaatOikein() {
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void edullinenKateisostoRahamaaraKasvaa() {
        paate.syoEdullisesti(400);
        assertEquals(100240, paate.kassassaRahaa());
    }
    
    @Test
    public void maukasKateisostoRahamaaraKasvaa() {
        paate.syoMaukkaasti(500);
        assertEquals(100400, paate.kassassaRahaa());
    }
    
    @Test
    public void edullinenKateisostoVaihtorahaOikein() {
        assertEquals(160, paate.syoEdullisesti(400));
    }
    
    @Test
    public void maukasKateisostoVaihtorahaOikein() {
        assertEquals(100, paate.syoMaukkaasti(500));
    }
    
    @Test
    public void myytyjenEdullistenLounaidenMaaraKasvaaJosMaksuRiittava() {
        paate.syoEdullisesti(500);
        assertEquals(1, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void myytyjenMaukkaidenLounaidenMaaraKasvaaJosMaksuRiittava() {
        paate.syoMaukkaasti(500);
        assertEquals(1, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void josEdullisenMaksuEiRiittavaKassanRahamaaraEiMuutu() {
        paate.syoEdullisesti(10);
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void josMaukkaanMaksuEiRiittavaKassanRahamaaraEiMuutu() {
        paate.syoMaukkaasti(10);
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void josEdullisenMaksuEiRiittavaVaihtorahatPalautetaan() {
        assertEquals(10, paate.syoEdullisesti(10));
    }
    
    @Test
    public void josMaukkaanMaksuEiRiittavaVaihtorahatPalautetaan() {
        assertEquals(10, paate.syoMaukkaasti(10));
    }
    
    @Test
    public void josEdullisenMaksuEiRiittavaMyytyjenMaaraEiMuutu() {
        paate.syoEdullisesti(10);
        assertEquals(0, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void josMaukkaanMaksuEiRiittavaMyytyjenMaaraEiMuutu() {
        paate.syoMaukkaasti(10);
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void edullisenLounaanKorttimaksuVeloitetaanJosRahaaTarpeeksi() {
        paate.syoEdullisesti(kortti);
        assertEquals(760, kortti.saldo());
    }
    
    @Test
    public void maukkaanLounaanKorttimaksuVeloitetaanJosRahaaTarpeeksi() {
        paate.syoMaukkaasti(kortti);
        assertEquals(600, kortti.saldo());
    }
    
    @Test
    public void edullisenLounaanKorttimaksuPalauttaaTrueJosRahaaTarpeeksi() {
        assertEquals(true, paate.syoEdullisesti(kortti));
    }
    
    @Test
    public void maukkaanLounaanKorttimaksuPalauttaaTrueJosRahaaTarpeeksi() {
        assertEquals(true, paate.syoMaukkaasti(kortti));
    }
    
    @Test
    public void myytyjenEdullistenLounaidenMaaraKasvaaJosKortillaTarpeeksiRahaa() {
        paate.syoEdullisesti(kortti);
        assertEquals(1, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void myytyjenMaukkaidenLounaidenMaaraKasvaaJosKortillaTarpeeksiRahaa() {
        paate.syoMaukkaasti(kortti);
        assertEquals(1, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void edullisellaLounaallaKortinSaldoEiMuutuJosEiTarpeeksiRahaa() {
        kortti.otaRahaa(800);
        paate.syoEdullisesti(kortti);
        assertEquals(200, kortti.saldo());
    }
    
    @Test
    public void maukkaallaLounaallaKortinSaldoEiMuutuJosEiTarpeeksiRahaa() {
        kortti.otaRahaa(800);
        paate.syoMaukkaasti(kortti);
        assertEquals(200, kortti.saldo());
    }
    
    @Test
    public void edullisellaLounaallaMyytyjenMaaraEiKasvaJosEiTarpeeksiRahaa() {
        kortti.otaRahaa(800);
        paate.syoEdullisesti(kortti);
        assertEquals(0, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukkaallaLounaallaMyytyjenMaaraEiKasvaJosEiTarpeeksiRahaa() {
        kortti.otaRahaa(800);
        paate.syoMaukkaasti(kortti);
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void edullisenLounaanKorttimaksuPalauttaaFalseJosEiTarpeeksiRahaa() {
        kortti.otaRahaa(800);
        assertEquals(false, paate.syoEdullisesti(kortti));
    }
    
    @Test
    public void maukkaanLounaanKorttimaksuPalauttaaFalseJosEiTarpeeksiRahaa() {
        kortti.otaRahaa(800);
        assertEquals(false, paate.syoMaukkaasti(kortti));
    }
    
    @Test
    public void edullisenLounaanKorttimaksuEiMuutaKassanRahamaaraa() {
        paate.syoEdullisesti(kortti);
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void maukkaanLounaanKorttimaksuEiMuutaKassanRahamaaraa() {
        paate.syoMaukkaasti(kortti);
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void kortilleLatausMuuttaaKortinSaldoa() {
        kortti.lataaRahaa(1000);
        assertEquals(2000, kortti.saldo());
    }
    
    @Test
    public void kortilleLatausKasvattaaKassanRahamaaraa() {
        paate.lataaRahaaKortille(kortti, 1000);
        assertEquals(101000, paate.kassassaRahaa());
    }

    @Test
    public void kortinLatausNegatiivisellaSummallaEiVaikutaKassaan() {
        paate.lataaRahaaKortille(kortti, -1000);
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void kortinLatausNegatiivisellaSummallaEiVaikutaKortinSaldoon() {
        paate.lataaRahaaKortille(kortti, -1000);
        assertEquals(1000, kortti.saldo());
    }
// TODO add test methods here.
// The methods must be annotated with annotation @Test. For example:
//
// @Test
// public void hello() {}
}
