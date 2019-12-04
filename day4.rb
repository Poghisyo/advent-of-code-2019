
def number_to_array(number)
  number.to_s.split("").map{|d| d.to_i }
end

def discard_decreasing(split_number)
  split_number.each_with_index do |v, i|
    if i == 0
      next
    elsif v < split_number[(i - 1)]
      return
    end
  end
  return split_number
end

def keep_if_adjacent_doubles(split_number)
  split_number.each_with_index do |v, i|
    if i == 0
      next
    elsif v == split_number[(i - 1)]
      return split_number
    end
  end
  return
end

def contains_only_double_repeats(split_number)
  keep = false
  split_number.each do |number|
    if split_number.count(number) == 1
      next
    else
      keep = true if split_number.count(number) / 2.0 == 1.0
    end
  end
  if keep
    return split_number
  else
    return
  end
end

def run_assignment
  range = (178416..676461).to_a
  rem_decr = []
  range.each do |number|
    p_num = number_to_array(number)
    rem_decr << discard_decreasing(p_num)
  end
  rem_decr.compact!
  r = []
  rem_decr.each do |n|
    # r << contains_only_double_repeats(n)
    r << keep_if_adjacent_doubles(n)
  end
  r.compact!
end

p run_assignment
