import com.litobumba.appgithub.model.User
import com.litobumba.appgithub.model.UserDetail
import com.litobumba.appgithub.repository.UserRepository
import com.litobumba.appgithub.ui.list_user.ListUserViewModel
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.IOException

@ExperimentalCoroutinesApi
class ListUserViewModelTest {

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")
    private lateinit var viewModel: ListUserViewModel
    private lateinit var repository: UserRepository

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        repository = mockk<UserRepository>()
        viewModel = ListUserViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
        clearAllMocks()
    }

    @Test
    fun `Deve Retornar a Lista de Usuários ao Pegar Usuários`() = runBlocking {
        val users = listOf(
            User(90806272,"lito-bumba", "https://avatars.githubusercontent.com/u/90806272?v=4"),
            User(90806272,"lito-bumba", "https://avatars.githubusercontent.com/u/90806272?v=4"),
        )
        coEvery { repository.getUsers() } returns users

        viewModel.getUsers()

        assert(viewModel.state.value.isLoading)
        delay(500L)
        assert(!viewModel.state.value.isLoading)
        assert(viewModel.state.value.users == users)

        coVerify { repository.getUsers() }
    }

    @Test
    fun `Deve Retornar Um Erro com uma Mensagem ao Pegar Usuários`() = runBlocking {
        coEvery { repository.getUsers() } throws IOException()

        viewModel.getUsers()

        assert(viewModel.state.value.isLoading)
        delay(500L)
        assert(!viewModel.state.value.isLoading)
        assert(viewModel.state.value.error == "Erro na Conexão, Verifica a Conexão com a Internet")

        coVerify { repository.getUsers() }
    }

    @Test
    fun `Deve Retornar o Nome de Usuário Digitado no Estado`() = runBlocking {
        val userName = "lito-bumba"

        viewModel.typingTextSearching(userName)

        assert(viewModel.searchState.value.textSearching == userName)
    }
}