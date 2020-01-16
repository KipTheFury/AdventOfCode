package aoc2017.day7;

import common.Node;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ProgramTreeMapperTest {

    private ProgramTreeMapper mapper = new ProgramTreeMapper();

    @Test
    void buildTree() {

        Map<String, Node<Integer>> tree = mapper.buildTree(testInstructions());

        assertThat(tree.get("tknk").getData()).isEqualTo(41);
        assertThat(tree.get("tknk").getChildren()).hasSize(3);

        assertThat(tree.get("ugml").getData()).isEqualTo(68);
        assertThat(tree.get("ugml").getChildren()).hasSize(3);

        assertThat(tree.get("padx").getData()).isEqualTo(45);
        assertThat(tree.get("padx").getChildren()).hasSize(3);

        assertThat(tree.get("fwft").getData()).isEqualTo(72);
        assertThat(tree.get("fwft").getChildren()).hasSize(3);

        assertThat(tree.get("pbga").getData()).isEqualTo(66);
        assertThat(tree.get("pbga").getChildren()).hasSize(0);

        assertThat(tree.get("gyxo").getData()).isEqualTo(61);
        assertThat(tree.get("gyxo").getChildren()).hasSize(0);

        assertThat(tree.get("ktlj").getData()).isEqualTo(57);
        assertThat(tree.get("ktlj").getChildren()).hasSize(0);
    }

    @Test
    void findRoot() {

        Map<String, Node<Integer>> tree = mapper.buildTree(testInstructions());

        assertThat(mapper.findRoot(tree)).isEqualTo("tknk");
    }

    @Test
    void findUnbalancedSubTrees() {

        Map<String, Node<Integer>> tree = mapper.buildTree(testInstructions());

        List<String> unbalancedTrees = mapper.findUnbalancedSubTrees(tree);

        assertThat(unbalancedTrees).hasSize(1);
        System.out.println(unbalancedTrees);
    }

    @Test
    void getSubtreeWeight() {

        Map<String, Node<Integer>> tree = mapper.buildTree(testInstructions());

        assertThat(mapper.getSubtreeWeight(tree.get("ugml"))).isEqualTo(251);
        assertThat(mapper.getSubtreeWeight(tree.get("padx"))).isEqualTo(243);
        assertThat(mapper.getSubtreeWeight(tree.get("fwft"))).isEqualTo(243);
    }

    private String[] testInstructions() {

        return new String[]{"pbga (66)", "xhth (57)", "ebii (61)", "havc (66)", "ktlj (57)", "fwft (72) -> ktlj, " +
                "cntj, xhth", "qoyq (66)", "padx (45) -> pbga, havc, qoyq", "tknk (41) -> ugml, padx, fwft", "jptl " +
                "(61)", "ugml (68) -> gyxo, ebii, jptl", "gyxo (61)", "cntj (57)"};
    }
}