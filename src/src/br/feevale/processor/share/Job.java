/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.br.feevale.processor.share;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Job extends Remote {
    String getJob() throws RemoteException;
    void sendJobResult(String result) throws RemoteException;
}
