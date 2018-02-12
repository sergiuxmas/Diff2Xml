import org.junit.Assert;
import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.builder.Input;
import org.xmlunit.diff.*;

import javax.xml.transform.Source;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;


/**
 * Created by IBM on 2/12/2018.
 */
public class ExtractDiffXml {
    public static void main(String[] args) {
        ExtractDiffXml diff=new ExtractDiffXml();
        diff.getDiff();
    }

    public void getDiff(){

            // reading two xml file to compare in Java program
            ClassLoader classLoader = getClass().getClassLoader();
            //FileInputStream fis1 = new FileInputStream(classLoader.getResource("input.xml").getFile());
            //FileInputStream fis2 = new FileInputStream(classLoader.getResource("output.xml").getFile());

            // using BufferedReader for improved performance
            //BufferedReader  source1 = new BufferedReader(new InputStreamReader(fis1));
            //BufferedReader  source2 = new BufferedReader(new InputStreamReader(fis2));

            Source s1 = Input.fromFile(classLoader.getResource("input.xml").getFile()).build();
            Source s2 = Input.fromFile(classLoader.getResource("output.xml").getFile()).build();

            //Diff d = DiffBuilder.compare(source1).build();
            DifferenceEngine diff = new DOMDifferenceEngine();
            diff.addDifferenceListener(new ComparisonListener() {
                public void comparisonPerformed(Comparison comparison, ComparisonResult outcome) {
                    //Assert.fail("found a difference: " + comparison);
                    System.out.println(comparison.getTestDetails().getXPath());
                }
            });

            diff.compare(s1, s2);
    }

}
