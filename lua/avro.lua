-- AvroContainer --
HEADER = 'Obj' .. '\1'

AvroContainer = { }

function AvroContainer:new(file)
  local inp = assert(io.open(file, "rb"))
  local data = inp:read(4)
  if not self:is_valid_avro_container(data) then
    error("Not a valid Avro container: " .. file)
  end

end

function AvroContainer:is_valid_avro_container(first_four_bytes)
  return HEADER == first_four_bytes
end

doctors = AvroContainer:new("./doctors.avro")

