/**
 * Logback: the reliable, fast and flexible logging library for Java.
 * 
 * Copyright (C) 1999-2006, QOS.ch
 * 
 * This library is free software, you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation.
 */

package joran.calculator;



import org.xml.sax.Attributes;

import ch.qos.logback.core.joran.action.Action;
import ch.qos.logback.core.joran.spi.ExecutionContext;
import ch.qos.logback.core.util.OptionHelper;


/**
 * ComputationAction1 will print the result of the compuration made by 
 * children elements but only if the compuration itself is named, that is if the
 * name attribute of the associated computation element is not null. In other
 * words, anonymous computations will not print their result.
 * 
 * @author Ceki G&uuml;lc&uuml;
 */
public class ComputationAction1 extends Action {
  public static String NAME_ATR = "name";

  String nameStr;

  /**
   * Store the value of the name attribute for future use.
   */
  public void begin(ExecutionContext ec, String name, Attributes attributes) {
    nameStr = attributes.getValue(NAME_ATR);
  }

  /**
   * Children elements have been processed. The sesults should be an integer 
   * placed at the top of the execution stack.
   * 
   * This value will be printed on the console but only if the action is 
   * named. Anonymous computation will not print their result.
   */
  public void end(ExecutionContext ec, String name) {
    if (OptionHelper.isEmpty(nameStr)) {
      // nothing to do
    } else {
      Integer i = (Integer) ec.peekObject();
      System.out.println(
        "The computation named [" + nameStr + "] resulted in the value " + i);
    }
  }
}
