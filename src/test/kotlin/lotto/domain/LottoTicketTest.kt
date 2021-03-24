package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class LottoTicketTest {
    @ParameterizedTest
    @CsvSource(
        "1, 3, 4",
        "5, 6, 11",
        "10, 10, 20"
    )
    fun `수동과 자동 티켓 개수 합 테스트`(manualSize: Int, automaticSize: Int, expectedSize: Int) {
        val manual = (1..manualSize).map {
            Lotto.from(LottoNumberTokenizer.tokenize("1,2,3,4,5,6").map { LottoNumber.from(it) })
        }
        val automatic = (1..automaticSize).map {
            Lotto.from(LottoNumberTokenizer.tokenize("1,2,3,4,5,6").map { LottoNumber.from(it) })
        }

        val lottoTicket = LottoTicket(
            manualLottos = manual,
            automaticLottos =  automatic,
            price = LottoPrice(99999999)
        )
        assertThat(lottoTicket.lottos.size).isEqualTo(expectedSize)
    }
}
