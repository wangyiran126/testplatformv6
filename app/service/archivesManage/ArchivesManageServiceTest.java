package service.archivesManage;

import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangyiran on 30/9/2016.
 */

/**
 * 性能测试
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
        for (Integer i = 0; i < times; i++) {
            list.remove(i);
        }
        return list;
    }


    @Benchmark
    public List<Integer> testArray() {
        List<Integer> list = new LinkedList<>();
        for (Integer i = 0; i < times; i++) {
           list.add(i);
        }
        for (Integer i = 0; i < times; i++) {
            list.add(i);
        }
        return list;
    }

//    public static void main(String[] args) throws RunnerException {
//        Options opt = new OptionsBuilder().include(ArchivesManageServiceTest.class.getSimpleName()).forks(1).build();
//        new Runner(opt).run();
//    }
}