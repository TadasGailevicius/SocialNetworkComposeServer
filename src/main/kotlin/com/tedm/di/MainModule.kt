package com.tedm.di

import com.tedm.data.repository.activity.ActivityRepository
import com.tedm.data.repository.activity.ActivityRepositoryImpl
import com.tedm.data.repository.comment.CommentRepository
import com.tedm.data.repository.comment.CommentRepositoryImpl
import com.tedm.data.repository.follow.FollowRepository
import com.tedm.data.repository.follow.FollowRepositoryImpl
import com.tedm.data.repository.likes.LikeRepository
import com.tedm.data.repository.likes.LikeRepositoryImpl
import com.tedm.data.repository.post.PostRepository
import com.tedm.data.repository.post.PostRepositoryImpl
import com.tedm.data.repository.user.UserRepository
import com.tedm.data.repository.user.UserRepositoryImpl
import com.tedm.service.*
import com.tedm.util.Constants.DATABASE_NAME
import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

val mainModule = module {
    single {
        val client = KMongo.createClient().coroutine
        client.getDatabase(DATABASE_NAME)
    }
    single<UserRepository> {
        UserRepositoryImpl(get())
    }

    single<FollowRepository> {
        FollowRepositoryImpl(get())
    }

    single<PostRepository> {
        PostRepositoryImpl(get())
    }

    single<LikeRepository> {
        LikeRepositoryImpl(get())
    }

    single<CommentRepository> {
        CommentRepositoryImpl(get())
    }

    single<ActivityRepository> {
        ActivityRepositoryImpl(get())
    }

    single { UserService(get(), get()) }
    single { FollowService(get()) }
    single { PostService(get()) }
    single { LikeService(get()) }
    single { CommentService(get()) }
    single { ActivityService(get(), get(), get()) }
}