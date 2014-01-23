package it.bioko.systema.misc;

import it.bioko.system.event.SystemListener;

public class TestShutdownListener implements SystemListener {

	public static Boolean triggered;

	@Override
	public void systemShutdown() {
		triggered = true;
	}

}
