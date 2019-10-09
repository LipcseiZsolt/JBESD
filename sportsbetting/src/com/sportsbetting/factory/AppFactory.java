/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsbetting.factory;

import com.sportsbetting.App;
import com.sportsbetting.service.SportsBettingService;
import com.sportsbetting.view.View;

/**
 *
 * @author Lipcsei Zsolt
 */
public class AppFactory {
    public static App getApp(){
        return new App(new SportsBettingService(), new View());
    }
}
