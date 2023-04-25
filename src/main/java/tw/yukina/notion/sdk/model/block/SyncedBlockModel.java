package tw.yukina.notion.sdk.model.block;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.model.common.unit.BlockUnit;

import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonDeserialize(using = JsonDeserializer.None.class)
public class SyncedBlockModel extends BlockModel {

    private static final String SYNCED_BLOCK_FIELD = "synced_block";

    @JsonProperty(SYNCED_BLOCK_FIELD)
    private Synced synced;

    @Override
    public boolean canHaveChildren() {
        return true;
    }

    @NotNull
    public static SyncedBlockModel of() {
        Synced synced = new Synced();
        SyncedBlockModel syncedBlock = new SyncedBlockModel(synced);
        syncedBlock.setType(BlockType.SYNCED_BLOCK);
        return syncedBlock;
    }

    @NotNull
    public static SyncedBlockModel of(String blockId) {
        Synced synced = new Synced();
        synced.setBlockUnit(BlockUnit.of(blockId));

        SyncedBlockModel syncedBlock = new SyncedBlockModel(synced);
        syncedBlock.setType(BlockType.SYNCED_BLOCK);
        return syncedBlock;
    }

    @NotNull
    public static SyncedBlockModel of(List<BlockModel> children) {
        Synced synced = new Synced();
        synced.setChildren(children);

        SyncedBlockModel syncedBlock = new SyncedBlockModel(synced);
        syncedBlock.setType(BlockType.SYNCED_BLOCK);
        return syncedBlock;
    }
}
