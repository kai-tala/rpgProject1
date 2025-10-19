/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.datastructures;

/**
 *
 * @author s576574
 */
public class ObjectNotOwnedException extends RuntimeException
{
    public ObjectNotOwnedException(){
        super();
    }
    public ObjectNotOwnedException(String msg){
        super(msg);
    }
}
