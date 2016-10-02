package service.archivesManage;

import org.junit.runner.RunWith;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangyiran on 30/9/2016.
 */
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@BenchmarkMode(Mode.AverageTime)
@OperationsPerInvocation(ArchivesManageServiceTest.times)
public class ArchivesManageServiceTest {
    public final static int times = 10000;

    static {
        System.out.println("hello");
    }

    @Benchmark
    public List<Integer> testList() {
        List<Integer> list = new ArrayList<>();
        for (Integer i = 0; i < times; i++) {
            list.add(i);
        }
        return list;
    }

    @Benchmark
    public Integer[] testArray() {
        Integer[] list = new Integer[times];
        for (Integer i = 0; i < times; i++) {
            list[0] = i;
        }
        return list;
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder().include(ArchivesManageServiceTest.class.getSimpleName()).forks(1).build();
        new Runner(opt).run();
    }
}