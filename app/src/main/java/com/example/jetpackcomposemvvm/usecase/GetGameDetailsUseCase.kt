package com.example.jetpackcomposemvvm.usecase

import com.example.jetpackcomposemvvm.repository.GamesRepository
import javax.inject.Inject

class GetGameDetailsUseCase @Inject constructor(private val gameRepository: GamesRepository) {}