export interface FlashPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;


  // check if flashlight available
  isAvailable(): Promise<{ value: boolean; }>;

  // turn on flash-light
  switchOn(options: {
    intensity?: number;
  }): Promise<void>;

  // turn off flash-light
  switchOff(): Promise<void>;

  // check flashlight state
  isSwitchedOn(): Promise<{ value: boolean; }>;

}
