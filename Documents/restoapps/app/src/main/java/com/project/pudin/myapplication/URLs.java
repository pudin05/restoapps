package com.project.pudin.myapplication;

/**
 * Created by WIN10 test on 11/27/2017.
 */

public class URLs {
    //http://192.168.43.225/depot/
    //http://subvitreous-helm.000webhostapp.com/
    //http://172.17.100.2/depot/
    //http://169.254.84.209/depot/
    //http://45.76.183.6/
    //http://putraput.eu.org/
    public static final String ROOT_URL = "http://putraput.eu.org/depot/";
    public static final String URL_LOGIN = ROOT_URL+"login.php";
    public static final String URL_PROFILE = ROOT_URL+"profile.php?user=";
    public static final String URL_REGIST = ROOT_URL+"regist.php";
    public static final String URL_EDITOR = ROOT_URL+"editprofile.php";
    public static final String URL_MENU = ROOT_URL+"menu.php?jenis=";
    public static final String URL_ADDTOCART = ROOT_URL+"cart.php?operator=insert";
    public static final String URL_READCART = ROOT_URL+"cart.php?operator=read";
    public static final String URL_EXECUTEPESAN = ROOT_URL+"pesan.php";
    public static final String URL_UPDATECART = ROOT_URL+"cart.php?operator=update";
    public static final String URL_DELETECART = ROOT_URL+"cart.php?operator=delete";
    public static final String URL_MYORDER = ROOT_URL+"pesanan.php";
}
