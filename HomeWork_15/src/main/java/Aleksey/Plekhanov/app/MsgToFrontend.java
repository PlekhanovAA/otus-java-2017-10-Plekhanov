package Aleksey.Plekhanov.app;

import Aleksey.Plekhanov.messageSystem.Address;
import Aleksey.Plekhanov.messageSystem.Addressee;
import Aleksey.Plekhanov.messageSystem.Message;

public abstract class MsgToFrontend extends Message {
    public MsgToFrontend(Address from, Address to) {
        super(from, to);
    }

    @Override
    public void exec(Addressee addressee) {
        if (addressee instanceof FrontendService) {
            exec((FrontendService) addressee);
        } else {
            //todo error!
        }
    }

    public abstract void exec(FrontendService frontendService);
}