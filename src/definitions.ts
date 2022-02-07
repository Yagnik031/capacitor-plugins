export interface FlashPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
