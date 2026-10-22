public class SampleTests {
    @Run public void additionWorks() { if (1 + 1 != 2) throw new RuntimeException("math broke"); System.out.println("  additionWorks ran"); }
    @Run public void stringLength() { if ("abc".length() != 3) throw new RuntimeException("bad length"); System.out.println("  stringLength ran"); }
    @Run public void failingTest() { throw new IllegalStateException("deliberate failure"); }
    public void notATest() { System.out.println("  this must NOT run"); }
}
