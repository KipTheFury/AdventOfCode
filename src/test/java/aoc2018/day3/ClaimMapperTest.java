package aoc2018.day3;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ClaimMapperTest {

    @Test
    void test_parseClaims() {

        ClaimMapper mapper = new ClaimMapper();
        String[] input = new String[]{"#1 @ 1,3: 4x4", "#2 @ 3,1: 4x4", "#3 @ 5,5: 2x2"};

        List<ClaimMapper.Claim> claims = mapper.parseClaims(input);

        assertThat(claims).hasSize(3);
    }

    @Test
    void test_findOverlaps() {

        ClaimMapper mapper = new ClaimMapper();
        String[] input = new String[]{"#1 @ 1,3: 4x4", "#2 @ 3,1: 4x4", "#3 @ 5,5: 2x2"};

        List<ClaimMapper.Claim> claims = mapper.parseClaims(input);
        assertThat(mapper.findOverlaps(claims)).isEqualTo(4);
    }
}