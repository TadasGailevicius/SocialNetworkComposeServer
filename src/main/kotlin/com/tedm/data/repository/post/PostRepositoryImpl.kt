package com.tedm.data.repository.post

import com.tedm.data.models.Following
import com.tedm.data.models.Post
import com.tedm.data.models.User
import org.litote.kmongo.`in`
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class PostRepositoryImpl(
    db: CoroutineDatabase
) : PostRepository {

    private val following = db.getCollection<Following>()
    private val posts = db.getCollection<Post>()
    private val users = db.getCollection<User>()

    override suspend fun createPostIfUserExist(post: Post): Boolean {
        val doesUserExist = users.findOneById(post.userId) != null
        if (!doesUserExist) {
            return false
        }
        posts.insertOne(post)
        return true
    }

    override suspend fun deletePost(postId: String) {
        posts.deleteOneById(postId)
    }

    override suspend fun getPostsByFollows(
        userId: String,
        page: Int,
        pageSize: Int
    ): List<Post> {
        val userIdsFromFollows = following.find(Following::followingUserId eq userId)
            .toList()
            .map {
                it.followedUserId
            }
        return posts.find(Post::userId `in` userIdsFromFollows)
            .skip(page * pageSize)
            .limit(pageSize)
            .descendingSort(Post::timestamp)
            .toList()
    }

    override suspend fun getPost(postId: String): Post? {
        return posts.findOneById(postId)
    }
}