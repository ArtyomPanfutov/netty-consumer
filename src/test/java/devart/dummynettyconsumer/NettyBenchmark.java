package devart.dummynettyconsumer;


import devart.dummynettyconsumer.service.DummyConsumerService;
import devart.dummynettyconsumer.service.DummyDto;
import org.openjdk.jmh.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

@SpringBootTest(classes = {devart.dummynettyconsumer.service.DummyConsumerService.class, devart.dummynettyconsumer.config.WebClientConfiguration.class})
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.SECONDS)
public class NettyBenchmark extends AbstractBenchmark {
    private static DummyConsumerService service;

    @Autowired
    void setDummyConsumerService(DummyConsumerService dummyConsumerService) {
        NettyBenchmark.service = dummyConsumerService;
    }

    /*
     * There is no @Test annotated method within here, because the AbstractBenchmark
     * defines one, which spawns the JMH runner. This class only contains JMH/Benchmark
     * related code.
     */
    @Setup(Level.Trial)
    public void setupBenchmark() {

    }

    @Benchmark
    public void getDummyDto() {
        DummyDto[] dummyDtos = service.get();
        System.out.println(dummyDtos.length);
    }
}
