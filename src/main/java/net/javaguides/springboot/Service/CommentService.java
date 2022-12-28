package net.javaguides.springboot.Service;

import net.javaguides.springboot.Model.Comment;
import net.javaguides.springboot.Model.Product;
import net.javaguides.springboot.Repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    public List<Comment> getAllComment(){
        return (List<Comment>) commentRepository.findAll();
    }
    public void adComment(Comment comment){
        commentRepository.save(comment);
    }
    public void removeCommentID(int id){
        commentRepository.deleteById(id);
    }

}
