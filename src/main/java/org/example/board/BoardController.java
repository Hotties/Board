package org.example.board;


import org.springframework.security.core.parameters.P;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("boards", boardService.findAll());
        return "list";
    }

    @GetMapping("/write")
    public String writeForm(){
        return "write";
    }

    // 게시글 작성 처리
    @PostMapping("/write")
    public String write(Board board) {
        boardService.write(board);
        return "redirect:/"; // 목록 페이지로 리다이렉트
    }

    // 게시글 상세 페이지
    @GetMapping("/view")
    public String view(@RequestParam("id") Long id, Model model) {
        model.addAttribute("board", boardService.findById(id));
        return "view"; // view.html 파일을 반환
    }

    // 게시글 삭제
    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id) {
        boardService.delete(id);
        return "redirect:/";
    }
    
    //로그인 페이지
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    //회원가입

    @GetMapping("/signup")
    public String signupForm() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(String username, String password, String email) {
        userService.createUser(username, password, email);
        return "redirect:/login";
    }
}
