package com.uiAutomatiom;

import org.testng.Assert;
import org.testng.annotations.Test;

public class B {

	@Test
	public void Test1B() {
		Assert.assertEquals(true, false);
	}

	@Test
	public void Test2() {
		Assert.assertEquals(true, true);
	}
}
