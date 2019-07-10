package leonsk32.myapp.myappback.restapi

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class FooController {
    @GetMapping("/api/foo")
    fun foo(): String {
        return "foo!!"
    }

    @GetMapping("/api/admin/hoge")
    fun admin(): String {
        return "admin!!"
    }
}