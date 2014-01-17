package org.jschema.test;

import gw.lang.reflect.IHasJavaClass;
import gw.lang.reflect.TypeSystem;
import junit.framework.TestSuite;

import java.util.ArrayList;
import java.util.List;

public class GosonScratchSuite extends TestSuite {

  private static String[] getTests() {
    return new String[]{
      "org.jschema.typeloader.JsonTypesTest",
      "org.jschema.typeloader.JSchemaTypesTest",
      "org.jschema.typeloader.InvoiceTest",
      "org.jschema.util.JSchemaUtilsTest",
      "org.jschema.typeloader.AssignabilityTest",
      "org.jschema.typeloader.JSchemaTypeLoaderTest",
    };
  }

  public GosonScratchSuite() {
    super(getTestClasses());
  }

  private static Class[] getTestClasses() {
    GosonSuite.maybeInitGosu();
    String[] testNames = getTests();
    List<Class> tests = new ArrayList<Class>();
    for (String test : testNames) {
      IHasJavaClass byFullName = (IHasJavaClass) TypeSystem.getByFullName(test);
      tests.add(byFullName.getBackingClass());
    }
    return tests.toArray(new Class[tests.size()]);
  }

  public static TestSuite suite() {
    return new GosonScratchSuite();
  }
}
