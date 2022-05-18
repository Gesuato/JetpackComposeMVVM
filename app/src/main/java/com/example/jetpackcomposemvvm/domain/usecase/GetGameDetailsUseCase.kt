package com.example.jetpackcomposemvvm.domain.usecase

import com.example.jetpackcomposemvvm.domain.repository.GamesRepository
import javax.inject.Inject

class GetGameDetailsUseCase @Inject constructor(private val gameRepository: GamesRepository) {}