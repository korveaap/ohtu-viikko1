package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstuktoriKahdellaParametrilla() {
        Varasto varasto2 = new Varasto(10,2);
        assertEquals(2, varasto2.getSaldo(), vertailuTarkkuus);
    }
    
     @Test
    public void konstuktoriKahdellaParametrillaAlkusaldoNegatiivinen() {
        Varasto varasto2 = new Varasto(10,-2);
        assertEquals(0, varasto2.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void konstuktoriKahdellaParametrillaTyhja() {
        Varasto varasto2 = new Varasto(0,0);
        assertEquals(0, varasto2.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void konstuktoriKahdellaParametrillaSaldoSuurempiKuinTilavuus() {
        Varasto varasto2 = new Varasto(10,12);
        assertEquals(10, varasto2.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriNollalla() {
        Varasto varasto2 = new Varasto(0);
        assertEquals(0, varasto2.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void lisataanNegatiivinen() {
        double saldo = varasto.getSaldo();
        varasto.lisaaVarastoon(-1);
        assertEquals(saldo, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void lisataanLiikaa() {
        varasto.lisaaVarastoon(100);
        assertEquals(varasto.getTilavuus(),varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test 
    public void otetaanNegatiivinen() {
        double otto = varasto.otaVarastosta(-1);
        assertEquals(0, otto, vertailuTarkkuus);
    }
    
    @Test
    public void otetaanLiikaaTarkistetaanOtto() {
        double saldo = varasto.getSaldo();
        double otto = varasto.otaVarastosta(100);
        assertEquals(saldo,otto,vertailuTarkkuus);
    }
    @Test
    public void otetaanLiikaaTarkistetaanSaldo() {
        double saldo = 0;
        double otto = varasto.otaVarastosta(100);
        assertEquals(saldo,otto,vertailuTarkkuus);
    }
    
    @Test
    public void tulostus() {
        String merkkijono = varasto.toString();
        assertEquals("saldo = 0.0, vielä tilaa 10.0", merkkijono);
    }
    
    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

}