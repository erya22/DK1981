package it.unibs.pajc.dk1981.DKvsMARIO1981.controller;

import it.unibs.pajc.dk1981.DKvsMARIO1981.model.DK;
import it.unibs.pajc.dk1981.DKvsMARIO1981.view.DKView;

public class DKController {
	private DK model;
	private DKView view;
	
	public DKController(DK model, DKView view) {
		this.model = model;
		this.view = view;
	}
	
	public void update() {
		model.setActionTimer(model.getActionTimer()+1);
		if (model.getActionTimer() >= model.getACTION_DURATION()) {
			model.setActionTimer(0);
			model.setActionIndex((model.getActionIndex()+1) % model.getActionCycle().length);
			model.setCurrentAction(model.getActionCycle()[model.getActionIndex()]);
			
			if (model.getCurrentAction().equals("dx")) {
//                model.launchBarrel();
            }
		}
		
		view.animate();
	}

}
