package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogTestController {

    //@Slf4j 어노테이션으로 대체 가능
    //private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest(){
        String name = "Spring";

        System.out.println("name = " + name);

        // 출력이 되지 않더라도 + 가 있어서 연산이 먼저 일어난다
        // 사용되지 않더라도 메모리가 할당됨
        // 잘못된 사용법
        log.trace("trace log = " + name);

        log.trace("trace log = {}", name);
//        log.debug("debug log = " + name);
//        log.info("info log = " + name);
//        log.warn("warn log = " + name);
//        log.error("error log = " + name);

        return "ok";
    }

}
