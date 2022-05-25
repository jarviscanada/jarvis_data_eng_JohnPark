package ca.jrvs.apps.practice;

import ca.jrvs.apps.grep.JavaGrepImpl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.w3c.dom.ls.LSOutput;

public class LambdaStreamExcImp implements LambdaStreamExc {

  public static void main(String[] args) {
    LambdaStreamExcImp lambdaStreamExcImp = new LambdaStreamExcImp();

    lambdaStreamExcImp.createStrStream("hi", "my", "name", "is").forEach(System.out::println);

    System.out.println("============toUpperCase Test============");
    lambdaStreamExcImp.toUpperCase("hi", "my", "name", "is").forEach(System.out::println);

    System.out.println("============lambdaStreamExcImp Test============");
    Stream<String> filterTestStream = lambdaStreamExcImp.createStrStream("Tell", "Tri", "Track",
        "Treck", "Tmp", "Thought");
    lambdaStreamExcImp.filter(filterTestStream, "Tr").forEach(System.out::println);

    System.out.println("============createIntStream Test============");
    int[] tmp = {1, 2, 3, 4, 5};
    lambdaStreamExcImp.createIntStream(tmp).forEach(System.out::println);

    System.out.println("==========printMessages Test===========");
    String[] messages = {"a", "b", "c"};
    lambdaStreamExcImp.printMessages(messages, lambdaStreamExcImp.getLambdaPrinter("msg:", "!"));

    System.out.println("==========flatNestedInt Test===========");
    int[] tmp1 = {1, 2, 3};
    int[] tmp2 = {3, 2, 1};
    Stream<List<Integer>> nestedInts = Stream.of(Arrays.asList(1, 2, 3), Arrays.asList(1, 2, 3));
    lambdaStreamExcImp.flatNestedInt(nestedInts).forEach(System.out::println);

  }

  @Override
  public Stream<String> createStrStream(String... strings) {
    return Arrays.stream(strings);
  }

  @Override
  public Stream<String> toUpperCase(String... strings) {
    return createStrStream(strings).map(String::toUpperCase);
  }

  @Override
  public Stream<String> filter(Stream<String> stringStream, String pattern) {
    return stringStream.filter(s -> !s.contains(pattern));
  }

  @Override
  public IntStream createIntStream(int[] arr) {
    return Arrays.stream(arr);
  }

  @Override
  public <E> List<E> toList(Stream<E> stream) {
    return stream.collect(Collectors.toList());
  }

  @Override
  public List<Integer> toList(IntStream intStream) {
    return intStream.boxed().collect(Collectors.toList());
  }

  @Override
  public IntStream createIntStream(int start, int end) {
    return IntStream.rangeClosed(start, end);
  }

  @Override
  public DoubleStream squareRootIntStream(IntStream intStream) {
    return intStream.mapToDouble(Math::sqrt);
  }

  @Override
  public IntStream getOdd(IntStream intStream) {
    return intStream.filter(i -> i % 2 != 0);
  }

  @Override
  public Consumer<String> getLambdaPrinter(String prefix, String suffix) {
    return msg -> System.out.println(prefix + msg + suffix);
  }

  @Override
  public void printMessages(String[] messages, Consumer<String> printer) {
    /*
     * String[] messages = {"a", "b", "c"};
     * lse.printMessages(messages, lse.getLambdaPrinter("msg:", "!"));
     */
    createStrStream(messages).forEach(printer);
  }

  @Override
  public void printOdd(IntStream intStream, Consumer<String> printer) {
    printMessages(getOdd(intStream).mapToObj(Integer::toString).toArray(String[]::new), printer);
  }

  @Override
  public Stream<Integer> flatNestedInt(Stream<List<Integer>> ints) {
//    List<Integer> flattenList = new ArrayList<>();
//    ints.forEach(flattenList::addAll);
//    return flattenList.stream().map(x -> x * x);
    return ints.flatMap(Collection::stream).map(value -> value * value);
  }
}
