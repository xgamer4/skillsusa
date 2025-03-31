# frozen_string_literal: true

class Player
  attr_reader :name, :dps_proficiency, :tank_proficiency, :support_proficiency

  def initialize(name)
    @name = name
    @dps_proficiency = rand(0..100)
    @tank_proficiency = rand(0..100)
    @support_proficiency = rand(0..100)
  end
end
