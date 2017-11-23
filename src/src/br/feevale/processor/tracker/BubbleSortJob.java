package src.br.feevale.processor.tracker;

import src.br.feevale.processor.share.Job;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class BubbleSortJob extends UnicastRemoteObject implements Job {
    protected BubbleSortJob() throws RemoteException {
        super();
    }

    @Override
    public String getJob() throws RemoteException {
        String result = (Tracker.jobs.size() > 0) ? Tracker.jobs.remove(0) : null;
        return result;
    }

    @Override
    public void sendJobResult(String result) throws RemoteException {
        Tracker.results.add(result);
        Tracker.listResults();
    }
}
