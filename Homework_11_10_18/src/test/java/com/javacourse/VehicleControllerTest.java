package com.javacourse;

import static org.junit.Assert.*;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.cglib.core.Local;

import static org.mockito.Mockito.*;

import java.util.*;

@RunWith(JUnitParamsRunner.class)
public class VehicleControllerTest {

    private static final int STARTING_YEAR = 1900;

    VehicleCollectionModel model = new VehicleCollectionModel();
    VehicleView view = new VehicleView();
    VehicleController vc=new VehicleController(view, model);

    @Test
    public void getLocaleBasedOnUsersChoice_returnsRightLocale(){
        String correctLang = "ukr";
        String correctLangCode= "3";

        Locale expected = new Locale(correctLang);
        Locale actual = vc.getLocaleBasedOnUsersChoice(correctLangCode);
        assertEquals(expected, actual);
    }

    @Test
    public void getLocaleBasedOnUsersChoice_languageNotSupported_returnsDefaultLocale(){
        String correctLang = "en";
        String correctLangCode= "-1";

        Locale expected = new Locale(correctLang);
        Locale actual = vc.getLocaleBasedOnUsersChoice(correctLangCode);
        assertEquals(expected, actual);
    }



    @Parameters
    public static Collection<Object[]> dataHeight() {
        return Arrays.asList(new Object[][]{
                {-5},
                {0},
                {440},
                {100001}
        });
    }

    @Test(expected = WrongParameterFromConsoleException.class)
    @Parameters(method = "dataHeight")
    public void checkHeight_wrongParam_exception(int h) {
        vc.checkHeight(h);
    }

    @Parameters
    public static Collection<Object[]> dataSpeed() {
        return Arrays.asList(new Object[][]{
                {-5},
                {0},
                {19},
                {2005}
        });
    }

    @Test(expected = WrongParameterFromConsoleException.class)
    @Parameters(method = "dataSpeed")
    public void checkSpeed_wrongParam_exception(int s) {
        vc.checkSpeed(s);
    }

    @Parameters
    public static Collection<Object[]> dataYear() {
        return Arrays.asList(new Object[][]{
                {-5},
                {0},
                {1899},
                {Calendar.getInstance().get(Calendar.YEAR)+1}
        });
    }

    @Test(expected = WrongParameterFromConsoleException.class)
    @Parameters(method = "dataYear")
    public void checkYear_wrongParam_exception(int y) {
        vc.checkYear(y);
    }

    @Parameters
    public static Collection<Object[]> dataAge() {
        return Arrays.asList(new Object[][]{
                {-5},
                {Calendar.getInstance().get(Calendar.YEAR) - STARTING_YEAR},
                {Calendar.getInstance().get(Calendar.YEAR)+1}
        });
    }

    @Test(expected = WrongParameterFromConsoleException.class)
    @Parameters(method = "dataAge")
    public void checkAge_wrongParam_exception(int a) {
        vc.checkYear(a);
    }

    @Parameters
    public static Collection<Object[]> wrongFirstParam() {
        return Arrays.asList(new Object[][]{
                {-110, -111},
                {1, -1},
                {111, 110}
        });
    }

    @Test(expected = WrongParameterFromConsoleException.class)
    @Parameters(method = "wrongFirstParam")
    public void checkFirstParamIsLessThanSecond_firstIsGreater_exception(int first, int second) {
        vc.checkFirstParamIsLessThanSecond(first, second);
    }

}