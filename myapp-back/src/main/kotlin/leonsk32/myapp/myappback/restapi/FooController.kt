package leonsk32.myapp.myappback.restapi

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/sec")
class FooController {
    @GetMapping("/foo")
    fun foo(): String {
        return "foo!!"
    }

    @GetMapping("/admin/hoge")
    fun admin(): String {
        return "admin!!"
    }
}