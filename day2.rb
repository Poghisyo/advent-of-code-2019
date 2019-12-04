INPUT = [1,0,0,3,1,1,2,3,1,3,4,3,1,5,0,3,2,1,6,19,1,9,19,23,2,23,10,27,1,27,5,31,1,31,6,35,1,6,35,39,2,39,13,43,1,9,43,47,2,9,47,51,1,51,6,55,2,55,10,59,1,59,5,63,2,10,63,67,2,9,67,71,1,71,5,75,2,10,75,79,1,79,6,83,2,10,83,87,1,5,87,91,2,9,91,95,1,95,5,99,1,99,2,103,1,103,13,0,99,2,14,0,0].freeze

def method_name(input, position)
  opcode = input[position]
  input_1_position = input[position + 1]
  input_2_position = input[position + 2]
  output = input[position + 3]

  case opcode
  when 1
    input[output] = input[input_1_position] + input[input_2_position]
  when 2
    input[output] = input[input_1_position] * input[input_2_position]
  when 99
    return input
  else
    return input
  end

  if (position + 4) > input.length
    return input
  else
    return method_name(input, position + 4)
  end
end

def find_solution(input)
  desired = 19690720
  input = INPUT.map{ |e| e }

  nouns = (0..99).to_a

  nouns.each do |noun|
    verbs = (0..99).to_a
    verbs.each do |verb|
      input[1] = noun
      input[2] = verb
      solution = method_name(input, 0)
      if solution[0] == desired
        p 'found'
        return 100 * noun + verb
      else
        input = INPUT.map{ |e| e }
        p 'continue search'
      end

    end
  end
end


p find_solution([1,0,0,3,1,1,2,3,1,3,4,3,1,5,0,3,2,1,6,19,1,9,19,23,2,23,10,27,1,27,5,31,1,31,6,35,1,6,35,39,2,39,13,43,1,9,43,47,2,9,47,51,1,51,6,55,2,55,10,59,1,59,5,63,2,10,63,67,2,9,67,71,1,71,5,75,2,10,75,79,1,79,6,83,2,10,83,87,1,5,87,91,2,9,91,95,1,95,5,99,1,99,2,103,1,103,13,0,99,2,14,0,0])
