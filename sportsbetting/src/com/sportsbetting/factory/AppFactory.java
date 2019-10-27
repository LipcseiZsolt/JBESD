/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsbetting.factory;

import com.sportsbetting.App;
import com.sportsbetting.service.SportsBettingServiceImpl;
import com.sportsbetting.view.ViewImpl;

/**
 *
 * @author Lipcsei Zsolt
 */
public final class AppFactory {
    
    private AppFactory() {}
    
    public static App getApp(){
        return new App(new SportsBettingServiceImpl(), new ViewImpl());
    }
    
}
