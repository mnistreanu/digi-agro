import { PinnedRowRendererModule } from './pinned-row-renderer.module';

describe('PinnedRowRendererModule', () => {
  let pinnedRowRendererModule: PinnedRowRendererModule;

  beforeEach(() => {
    pinnedRowRendererModule = new PinnedRowRendererModule();
  });

  it('should create an instance', () => {
    expect(pinnedRowRendererModule).toBeTruthy();
  });
});
