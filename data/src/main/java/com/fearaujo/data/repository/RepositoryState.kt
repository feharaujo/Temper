package com.fearaujo.data.repository

sealed class RepositoryState {
    object Loading : RepositoryState()
    object Success : RepositoryState()
    object OnError : RepositoryState()
}