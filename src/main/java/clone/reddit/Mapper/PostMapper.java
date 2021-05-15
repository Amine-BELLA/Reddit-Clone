package clone.reddit.Mapper;

import clone.reddit.Model.Post;
import clone.reddit.Model.Subreddit;
import clone.reddit.Model.User;
import clone.reddit.dto.PostRequest;
import clone.reddit.dto.PostResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PostMapper {
    @Mappings({
        @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())"),
        @Mapping(target = "subreddit", source = "subreddit"),
        @Mapping(target = "user", source = "user"),
        @Mapping(target = "description", source = "postRequest.description")
    })
    Post mapRequestToPost(PostRequest postRequest, Subreddit subreddit, User user);

    @Mappings({
        @Mapping(target = "id", source="postId"),
        @Mapping(target = "username", source = "user.username"),
        @Mapping(target = "subredditName", source = "subreddit.name"),
        @Mapping(target = "description", source = "description")
    })
    PostResponse mapToResponse(Post post);
}
