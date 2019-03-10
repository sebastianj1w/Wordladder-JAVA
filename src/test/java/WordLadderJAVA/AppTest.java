package WordLadderJAVA;

import static org.junit.Assert.assertTrue;

//import jdk.internal.vm.compiler.collections.EconomicMap;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;
import java.util.Stack;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
//    @Test
//    public void shouldAnswerWithTrue()
//    {
//        assertTrue( true );
//    }

    @Test
    public void testLoadDict() throws Exception {
        App a = new App();
        Set<String> dict = App.loadDict("./dictionary.txt");
        Assert.assertTrue(!dict.isEmpty());
        Assert.assertTrue(dict.contains("aal"));
    }

    @Test
    public void testLoadDictFail() throws Exception {
        App a = new App();
        Set<String> dict = App.loadDict("./dictionasssry.txt");
        Assert.assertTrue(dict.isEmpty());
    }

    @Test
    public void testCheck() throws Exception {
        App a = new App();
        Set<String> dict = App.loadDict("./dictionary.txt");
        String wa, wb;
        wa = "come";
        wb = "back";
        int ans = App.checkValidity(wa, wb, dict);
        Assert.assertEquals(ans, 0);
        wa = "came";
        wb = "back";
        ans = App.checkValidity(wa, wb, dict);
        Assert.assertEquals(ans, 0);
        wa = "come";
        wb = "buck";
        ans = App.checkValidity(wa, wb, dict);
        Assert.assertEquals(ans, 1);
        wa = "come";
        wb = "come";
        ans = App.checkValidity(wa, wb, dict);
        Assert.assertEquals(ans, 1);
        wa = "buck";
        wb = "aaal";
        ans = App.checkValidity(wa, wb, dict);
        Assert.assertEquals(ans, 1);
        wa = "where";
        wb = "back";
        ans = App.checkValidity(wa, wb, dict);
        Assert.assertEquals(ans, 1);
        wb = "where";
        wa = "back";
        ans = App.checkValidity(wa, wb, dict);
        Assert.assertEquals(ans, 1);
    }

    @Test
    public void testFindingLadder() throws Exception{
        App a = new App();
        Set<String> dict = App.loadDict("./dictionary.txt");
        String wa, wb;
        wb = "come";
        wa = "back";
        Stack<String> ans0 = App.findLadder(wa, wb, dict);
        Assert.assertTrue(!ans0.isEmpty());
//        Assert.assertEquals(ans, [come, dome, dame, dace, dack, back]);
        wb = "back";
        wa = "come";
        Stack<String> ans1 = App.findLadder(wa, wb, dict);
        Assert.assertTrue(!ans0.isEmpty());
        Assert.assertNotEquals(ans0, ans1);
    }
}
